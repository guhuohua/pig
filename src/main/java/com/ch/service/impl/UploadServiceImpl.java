package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.base.UploadName;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsTypeMapper;
import com.ch.dao.SpecificationAttributeMapper;
import com.ch.dao.SpecificationMapper;
import com.ch.entity.*;
import com.ch.service.UploadService;
import com.ch.util.ExcelHelper;
import com.ch.util.OSSUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    OSSUtil ossUtil;
    @Autowired
    GoodsTypeMapper goodsTypeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    SpecificationMapper specificationMapper;
    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;

    @Override
    public ResponseResult uploadFile(MultipartFile file) {
        ResponseResult result = new ResponseResult();
        UploadName uploadName = new UploadName();
        String upload = ossUtil.upload(file);
        uploadName.setUrl(upload);
        uploadName.setName(file.getOriginalFilename());
        result.setData(uploadName);
        return result;
    }

    @Override
    public ResponseResult uploadGoods(MultipartFile file) {
        ResponseResult result = new ResponseResult();

        String fileSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
        if (fileSuffix.toLowerCase().equals("xls") || fileSuffix.toLowerCase().equals("xlsx")) {
            try {
                List<String> list = ExcelHelper.exportListFromExcel(file.getInputStream(), fileSuffix, 0);
                for (int i = 1; i < list.size(); i++) {
                    Goods goods = new Goods();
                    String[] str = list.get(i).split("\\|", -1);
                    if (BeanUtils.isNotEmpty(str[1])) {
                        goods.setTitle(str[1]);
                    }
                    if (BeanUtils.isNotEmpty(str[2])) {
                        goods.setInventory(Integer.parseInt(str[2]));
                    }
                    if (BeanUtils.isNotEmpty(str[3])) {
                        goods.setName(str[3]);
                    }
                    if (BeanUtils.isNotEmpty(str[4])) {
                        GoodsTypeExample example = new GoodsTypeExample();
                        GoodsTypeExample.Criteria criteria = example.createCriteria();
                        criteria.andTitleEqualTo(str[4]);
                        criteria.andParentIdEqualTo(0);
                        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(example);
                        if (goodsTypes.size() == 0) {
                            GoodsType goodsType = new GoodsType();
                            goodsType.setParentId(0);
                            goodsType.setTitle(str[4]);
                            goodsTypeMapper.insert(goodsType);
                        }
                    }
                    if (BeanUtils.isNotEmpty(str[5])) {
                        GoodsTypeExample example1 = new GoodsTypeExample();
                        GoodsTypeExample.Criteria criteria1 = example1.createCriteria();
                        criteria1.andTitleEqualTo(str[4]);
                        criteria1.andParentIdEqualTo(0);
                        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(example1);
                        if (goodsTypes.size() > 0) {
                            GoodsType goodsType = goodsTypes.get(0);
                            GoodsTypeExample example2 = new GoodsTypeExample();
                            GoodsTypeExample.Criteria criteria2 = example2.createCriteria();
                            criteria2.andParentIdEqualTo(goodsType.getId());
                            criteria2.andTitleEqualTo(str[5]);

                            List<GoodsType> goodsTypes1 = goodsTypeMapper.selectByExample(example2);
                            if (goodsTypes1.size() == 0) {
                                GoodsType goodsType1 = new GoodsType();
                                goodsType1.setParentId(goodsType.getId());
                                goodsType1.setTitle(str[5]);
                                goodsTypeMapper.insert(goodsType1);
                            }

                        }
                    }
                    if (BeanUtils.isNotEmpty(str[6])) {
                        goods.setGoodsType(str[6]);
                    }

                    goodsMapper.insert(goods);

                }


            } catch (IOException e) {

            }
        }

        return result;
    }

    @Override
    public ResponseResult uploadSpecification(MultipartFile file) {
        ResponseResult result = new ResponseResult();

        String fileSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
        if (fileSuffix.toLowerCase().equals("xls") || fileSuffix.toLowerCase().equals("xlsx")) {
            try {
                List<String> list = ExcelHelper.exportListFromExcel(file.getInputStream(), fileSuffix, 0);
                for (int i = 1; i < list.size(); i++) {

                    String[] str = list.get(i).split("\\|", -1);
                    if (BeanUtils.isNotEmpty(str[1])) {
                        SpecificationExample example = new SpecificationExample();
                        SpecificationExample.Criteria criteria = example.createCriteria();
                        criteria.andTitleEqualTo(str[1]);
                        List<Specification> specifications = specificationMapper.selectByExample(example);
                        if (specifications.size() == 0) {
                            Specification specification = new Specification();
                            specification.setTitle(str[1]);
                            specificationMapper.insert(specification);
                            String[] split = str[2].split(",");
                            for (String s : split) {
                                SpecificationAttribute specificationAttribute = new SpecificationAttribute();
                                specificationAttribute.setSpecificationId(specification.getId());
                                specificationAttribute.setName(s);
                                specificationAttributeMapper.insert(specificationAttribute);

                            }


                        }
                    }

                }


            } catch (IOException e) {

            }
        }

        return result;
    }

    @Override
    public UploadName upload(MultipartFile file) {
        UploadName result = new UploadName();
        String upload = ossUtil.upload(file);
        result.setUrl(upload);
        result.setName(file.getOriginalFilename());
        return result;
    }
}
