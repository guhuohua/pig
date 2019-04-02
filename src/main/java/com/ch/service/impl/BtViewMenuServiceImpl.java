package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.BtViewMenuEngMapper;
import com.ch.dao.BtViewMenuFanMapper;
import com.ch.dao.BtViewMenuMapper;
import com.ch.dto.MenuParam;
import com.ch.entity.*;

import com.ch.service.BtViewMenuService;
import com.ch.util.BaiduTranslateUtil;
import com.ch.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BtViewMenuServiceImpl implements BtViewMenuService {
    @Autowired
    private BtViewMenuMapper btViewMenuMapper;

    @Autowired
    BtViewMenuEngMapper btViewMenuEngMapper;



    @Autowired
    BaiduTranslateUtil baiduTranslateUtil;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BtViewMenuFanMapper btViewMenuFanMapper;

    @Override
    public long countByExample(BtViewMenuExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(BtViewMenuExample example) {
        return 0;
    }

    @Override
    public ResponseResult insert(BtViewMenu record) {
        ResponseResult result = new ResponseResult();
        record.setCreateTime(new Date());
        btViewMenuMapper.insert(record);

        BtViewMenuEng btViewMenuEng = new BtViewMenuEng();
        modelMapper.map(record, btViewMenuEng);
        btViewMenuEng.setName(baiduTranslateUtil.translate(record.getName()));
        btViewMenuEngMapper.insert(btViewMenuEng);

        BtViewMenuFan btViewMenuFan = new BtViewMenuFan();
        modelMapper.map(record, btViewMenuFan);
        btViewMenuFan.setName(baiduTranslateUtil.translateFan(record.getName()));
        btViewMenuFanMapper.insert(btViewMenuFan);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult deleteByPrimaryKey(String id) {
        ResponseResult result = new ResponseResult();

        BtViewMenu btViewMenu = btViewMenuMapper.selectByPrimaryKey(id);
        if(btViewMenu.getParentId().equals("0")){

            BtViewMenuExample example = new BtViewMenuExample();
            BtViewMenuExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            List<BtViewMenu> btViewMenus = btViewMenuMapper.selectByExample(example);
           if (btViewMenus!=null){
               for (BtViewMenu btViewMenu2 :btViewMenus){
                   BtViewMenuExample example1 = new BtViewMenuExample();
                   BtViewMenuExample.Criteria criteria1 = example1.createCriteria();
                   criteria1.andParentIdEqualTo(btViewMenu2.getId());
                   List<BtViewMenu> btViewMenus1 = btViewMenuMapper.selectByExample(example1);

                   for ( BtViewMenu btViewMenu1 :btViewMenus1){
                       btViewMenuMapper.deleteByPrimaryKey(btViewMenu1.getId());
                       btViewMenuEngMapper.deleteByPrimaryKey(btViewMenu1.getId());
                       btViewMenuFanMapper.deleteByPrimaryKey(btViewMenu1.getId());
                   }


                   btViewMenuMapper.deleteByPrimaryKey(btViewMenu2.getId());
                   btViewMenuEngMapper.deleteByPrimaryKey(btViewMenu2.getId());
                   btViewMenuFanMapper.deleteByPrimaryKey(btViewMenu2.getId());

               }
           }else {
               btViewMenuMapper.deleteByPrimaryKey(id);
               btViewMenuEngMapper.deleteByPrimaryKey(id);
               btViewMenuFanMapper.deleteByPrimaryKey(id);

           }
            btViewMenuMapper.deleteByPrimaryKey(id);
            btViewMenuEngMapper.deleteByPrimaryKey(id);
            btViewMenuFanMapper.deleteByPrimaryKey(id);
        }else {
            BtViewMenuExample example = new BtViewMenuExample();
            BtViewMenuExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            BtViewMenuEngExample engExample = new BtViewMenuEngExample();
            engExample.createCriteria().andParentIdEqualTo(id);
            BtViewMenuFanExample fanExample = new BtViewMenuFanExample();

            fanExample.createCriteria().andParentIdEqualTo(id);
            List<BtViewMenu> btViewMenus = btViewMenuMapper.selectByExample(example);
            if (btViewMenus!=null){
                for (BtViewMenu btViewMenu2 :btViewMenus){
                    btViewMenuMapper.deleteByExample(example);
                    btViewMenuEngMapper.deleteByExample(engExample);
                    btViewMenuFanMapper.deleteByExample(fanExample);

                }
                btViewMenuMapper.deleteByPrimaryKey(id);
                btViewMenuEngMapper.deleteByPrimaryKey(id);
                btViewMenuFanMapper.deleteByPrimaryKey(id);
            }else {
                btViewMenuMapper.deleteByPrimaryKey(id);
                btViewMenuEngMapper.deleteByPrimaryKey(id);
                btViewMenuFanMapper.deleteByPrimaryKey(id);
            }
        }

        return result;
    }

    @Override
    public int insertSelective(BtViewMenu record) {
        return 0;
    }

    @Override
    public List<BtViewMenu> selectByExample(BtViewMenuExample example) {
        return null;
    }

    @Override
    public int updateByExampleSelective(BtViewMenu record, BtViewMenuExample example) {
        return 0;
    }

    @Override
    public int updateByExample(BtViewMenu record, BtViewMenuExample example) {
        return 0;
    }

    @Override
    public ResponseResult findTree() {
        ResponseResult result = new ResponseResult();
        try {//查询所有菜单
            List<BtViewMenu> allMenu = btViewMenuMapper.selectByExample(null);
           for(BtViewMenu btViewMenu : allMenu){

               BtViewMenuExample example = new BtViewMenuExample();
               BtViewMenuExample.Criteria criteria = example.createCriteria();
               criteria.andIdEqualTo (btViewMenu.getParentId());
               List<BtViewMenu> btViewMenus = btViewMenuMapper.selectByExample(example);
               for (BtViewMenu btViewMenu1 : btViewMenus){
                   btViewMenu.setParentName(btViewMenu1.getName());
               }

           }
            //根节点
            List<BtViewMenu> rootMenu = new ArrayList<BtViewMenu>();
            for (BtViewMenu nav : allMenu) {
                if (nav.getParentId().equals("0")) {
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (BtViewMenu nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<BtViewMenu> childList = getChild(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            result.setCode(0);

            result.setData(rootMenu);
            return result;

        } catch (Exception e) {
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("菜单生成异常");
            return result;
        }


    }


    private List<BtViewMenu> getChild(String id, List<BtViewMenu> allMenu) {
        //子菜单
        List<BtViewMenu> childList = new ArrayList<BtViewMenu>();
        for (BtViewMenu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (BtViewMenu nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        Collections.sort(childList, order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<BtViewMenu>();
        }
        return childList;
    }

    public Comparator<BtViewMenu> order() {
        Comparator<BtViewMenu> comparator = new Comparator<BtViewMenu>() {
            @Override
            public int compare(BtViewMenu o1, BtViewMenu o2) {
                if (o1.getSortOrder() != o2.getSortOrder()) {
                    return o1.getSortOrder() - o2.getSortOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    /**
     * pageNum:当前页码
     * pageSize:每页记录数
     */
    public ResponseResult findPageTree(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ResponseResult result = findTree();
        List<BtViewMenu> data = (List<BtViewMenu>) result.getData();
        PageInfo<BtViewMenu> page = new PageInfo<>(data);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult findPage(MenuParam menuParam) {
        PageHelper.startPage(menuParam.getPageNum(), menuParam.getPageSize());
        BtViewMenuExample example = new BtViewMenuExample();
        BtViewMenuExample.Criteria criteria = example.createCriteria();
        if (menuParam.getName() != null) {
            criteria.andNameLike("%" + menuParam.getName() + "%");

        }
        List<BtViewMenu> btViewMenus = btViewMenuMapper.selectByExample(example);
        PageInfo<BtViewMenu> page = new PageInfo<>(btViewMenus);
        ResponseResult result = new ResponseResult();
        result.setData(page);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult updateByPrimaryKey(BtViewMenu btViewMenu) {
        ResponseResult result = new ResponseResult();

        btViewMenuMapper.updateByPrimaryKey(btViewMenu);

        BtViewMenuEng btViewMenuEng = btViewMenuEngMapper.findById(btViewMenu.getId());
        btViewMenuEng.setName(baiduTranslateUtil.translate(btViewMenu.getName()));
        btViewMenuEng.setPage(btViewMenu.getPage());
        btViewMenuEngMapper.updateByPrimaryKey(btViewMenuEng);

        BtViewMenuFan btViewMenuFan = btViewMenuFanMapper.findById(btViewMenu.getId());
        btViewMenuFan.setPage(btViewMenu.getPage());
        btViewMenuFan.setName(baiduTranslateUtil.translateFan(btViewMenu.getName()));
        btViewMenuFanMapper.updateByPrimaryKey(btViewMenuFan);

        return result;
    }


    @Override
    public ResponseResult findTreeEng() {
        ResponseResult result = new ResponseResult();
        try {//查询所有菜单
            List<BtViewMenuEng> allMenu = btViewMenuEngMapper.selectByExample(null);
            //根节点
            List<BtViewMenuEng> rootMenu = new ArrayList<BtViewMenuEng>();
            for (BtViewMenuEng nav : allMenu) {
                if (nav.getParentId().equals("0")) {
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, orderEng());
            //为根菜单设置子菜单，getClild是递归调用的
            for (BtViewMenuEng nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<BtViewMenuEng> childList = getChildEng(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            result.setCode(0);

            result.setData(rootMenu);
            return result;

        } catch (Exception e) {
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("菜单生成异常");
            return result;
        }
    }

    @Override
    public ResponseResult findTreeFan() {
        ResponseResult result = new ResponseResult();
        try {//查询所有菜单
            List<BtViewMenuFan> allMenu = btViewMenuFanMapper.selectByExample(null);

            //根节点
            List<BtViewMenuFan> rootMenu = new ArrayList<BtViewMenuFan>();
            for (BtViewMenuFan nav : allMenu) {
                if (nav.getParentId().equals("0")) {
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, orderFan());
            //为根菜单设置子菜单，getClild是递归调用的
            for (BtViewMenuFan nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<BtViewMenuFan> childList = getChildFan(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            result.setCode(0);

            result.setData(rootMenu);
            return result;

        } catch (Exception e) {
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("菜单生成异常");
            return result;
        }
    }


    private List<BtViewMenuEng> getChildEng(String id, List<BtViewMenuEng> allMenu) {
        //子菜单
        List<BtViewMenuEng> childList = new ArrayList<BtViewMenuEng>();
        for (BtViewMenuEng nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (BtViewMenuEng nav : childList) {
            nav.setChildren(getChildEng(nav.getId(), allMenu));
        }
        Collections.sort(childList, orderEng());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<BtViewMenuEng>();
        }
        return childList;
    }




    public Comparator<BtViewMenuEng> orderEng() {
        Comparator<BtViewMenuEng> comparator = new Comparator<BtViewMenuEng>() {
            @Override
            public int compare(BtViewMenuEng o1, BtViewMenuEng o2) {
                if (o1.getSortOrder() != o2.getSortOrder()) {
                    return o1.getSortOrder() - o2.getSortOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    public Comparator<BtViewMenuFan> orderFan() {
        Comparator<BtViewMenuFan> comparator = new Comparator<BtViewMenuFan>() {
            @Override
            public int compare(BtViewMenuFan o1, BtViewMenuFan o2) {
                if (o1.getSortOrder() != o2.getSortOrder()) {
                    return o1.getSortOrder() - o2.getSortOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    private List<BtViewMenuFan> getChildFan(String id, List<BtViewMenuFan> allMenu) {
        //子菜单
        List<BtViewMenuFan> childList = new ArrayList<BtViewMenuFan>();
        for (BtViewMenuFan nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (BtViewMenuFan nav : childList) {
            nav.setChildren(getChildFan(nav.getId(), allMenu));
        }
        Collections.sort(childList, orderFan());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<BtViewMenuFan>();
        }
        return childList;
    }



}
