package com.songzi.yummy.controller.restaurant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class RestaurantFoodController extends BaseController {
    //转到后台管理-产品添加页-ajax
    @RequestMapping(value = "restaurant/food/new",method = RequestMethod.GET)
    public String goToAddPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object managerId = checkManage(session);
        if(managerId == null){
            return "manage/include/loginMessage";
        }

        logger.info("获取分类列表");
        /*List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);
        logger.info("获取第一个分类信息对应的属性列表");
        List<Property> propertyList = propertyService.getList(new Property().setProperty_category(categoryList.get(0)),null);
        map.put("propertyList",propertyList);

        logger.info("转到后台管理-产品添加页-ajax方式");*/
        return "admin/include/productDetails";
    }

    //添加产品信息-ajax.
    @ResponseBody
    @RequestMapping(value = "admin/product", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addProduct(@RequestParam String product_name/* 产品名称 */,
                             @RequestParam String product_title/* 产品标题 */,
                             @RequestParam Integer product_category_id/* 产品类型ID */,
                             @RequestParam Double product_sale_price/* 产品最低价 */,
                             @RequestParam Double product_price/* 产品最高价 */,
                             @RequestParam Byte product_isEnabled/* 产品状态 */,
                             @RequestParam String propertyJson/* 产品属性JSON */,
                             @RequestParam(required = false) String[] productSingleImageList/*产品预览图片名称数组*/,
                             @RequestParam(required = false) String[] productDetailsImageList/*产品详情图片名称数组*/) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合产品信息");
        /*Product product = new Product()
                .setProduct_name(product_name)
                .setProduct_title(product_title)
                .setProduct_category(new Category().setCategory_id(product_category_id))
                .setProduct_sale_price(product_sale_price)
                .setProduct_price(product_price)
                .setProduct_isEnabled(product_isEnabled)
                .setProduct_create_date(new Date());
        logger.info("添加产品信息");
        boolean yn = productService.add(product);
        if (!yn) {
            logger.warn("产品添加失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        int product_id = lastIDService.selectLastID();
        logger.info("添加成功！,新增产品的ID值为：{}", product_id);

        JSONObject object = JSON.parseObject(propertyJson);
        Set<String> propertyIdSet = object.keySet();
        if (propertyIdSet.size() > 0) {
            logger.info("整合产品子信息-产品属性");
            List<PropertyValue> propertyValueList = new ArrayList<>(5);
            for (String key : propertyIdSet) {
                String value = object.getString(key);
                PropertyValue propertyValue = new PropertyValue()
                        .setPropertyValue_value(value)
                        .setPropertyValue_property(new Property().setProperty_id(Integer.valueOf(key)))
                        .setPropertyValue_product(new Product().setProduct_id(product_id));
                propertyValueList.add(propertyValue);
            }
            logger.info("共有{}条产品属性数据", propertyValueList.size());
            yn = propertyValueService.addList(propertyValueList);
            if (yn) {
                logger.info("产品属性添加成功！");
            } else {
                logger.warn("产品属性添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        if (productSingleImageList != null && productSingleImageList.length > 0) {
            logger.info("整合产品子信息-产品预览图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productSingleImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 0)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(new Product().setProduct_id(product_id))
                );
            }
            logger.info("共有{}条产品预览图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品预览图片添加成功！");
            } else {
                logger.warn("产品预览图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }

        if (productDetailsImageList != null && productDetailsImageList.length > 0) {
            logger.info("整合产品子信息-产品详情图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productDetailsImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 1)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(new Product().setProduct_id(product_id))
                );
            }
            logger.info("共有{}条产品详情图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品详情图片添加成功！");
            } else {
                logger.warn("产品详情图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        logger.info("产品信息及其子信息添加成功！");
        jsonObject.put("success", true);
        jsonObject.put("product_id", product_id);
*/
        return jsonObject.toJSONString();
    }

    //更新产品信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/product/{product_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateProduct(@RequestParam String product_name/* 产品名称 */,
                                @RequestParam String product_title/* 产品标题 */,
                                @RequestParam Integer product_category_id/* 产品类型ID */,
                                @RequestParam Double product_sale_price/* 产品最低价 */,
                                @RequestParam Double product_price/* 产品最高价 */,
                                @RequestParam Byte product_isEnabled/* 产品状态 */,
                                @RequestParam String propertyAddJson/* 产品添加属性JSON */,
                                @RequestParam String propertyUpdateJson/* 产品更新属性JSON */,
                                @RequestParam(required = false) Integer[] propertyDeleteList/* 产品删除属性ID数组 */,
                                @RequestParam(required = false) String[] productSingleImageList/*产品预览图片名称数组*/,
                                @RequestParam(required = false) String[] productDetailsImageList/*产品详情图片名称数组*/,
                                @PathVariable("product_id") Integer product_id/* 产品ID */) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合产品信息");
        /*Product product = new Product()
                .setProduct_id(product_id)
                .setProduct_name(product_name)
                .setProduct_title(product_title)
                .setProduct_category(new Category().setCategory_id(product_category_id))
                .setProduct_sale_price(product_sale_price)
                .setProduct_price(product_price)
                .setProduct_isEnabled(product_isEnabled)
                .setProduct_create_date(new Date());
        logger.info("更新产品信息，产品ID值为：{}", product_id);
        boolean yn = productService.update(product);
        if (!yn) {
            logger.info("产品信息更新失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        logger.info("产品信息更新成功！");

        JSONObject object = JSON.parseObject(propertyAddJson);
        Set<String> propertyIdSet = object.keySet();
        if (propertyIdSet.size() > 0) {
            logger.info("整合产品子信息-需要添加的产品属性");
            List<PropertyValue> propertyValueList = new ArrayList<>(5);
            for (String key : propertyIdSet) {
                String value = object.getString(key);
                PropertyValue propertyValue = new PropertyValue()
                        .setPropertyValue_value(value)
                        .setPropertyValue_property(new Property().setProperty_id(Integer.valueOf(key)))
                        .setPropertyValue_product(product);
                propertyValueList.add(propertyValue);
            }
            logger.info("共有{}条需要添加的产品属性数据", propertyValueList.size());
            yn = propertyValueService.addList(propertyValueList);
            if (yn) {
                logger.info("产品属性添加成功！");
            } else {
                logger.warn("产品属性添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }

        object = JSON.parseObject(propertyUpdateJson);
        propertyIdSet = object.keySet();
        if (propertyIdSet.size() > 0) {
            logger.info("整合产品子信息-需要更新的产品属性");
            List<PropertyValue> propertyValueList = new ArrayList<>(5);
            for (String key : propertyIdSet) {
                String value = object.getString(key);
                PropertyValue propertyValue = new PropertyValue()
                        .setPropertyValue_value(value)
                        .setPropertyValue_id(Integer.valueOf(key));
                propertyValueList.add(propertyValue);
            }
            logger.info("共有{}条需要更新的产品属性数据", propertyValueList.size());
            for (int i = 0; i < propertyValueList.size(); i++) {
                logger.info("正在更新第{}条，共{}条", i + 1, propertyValueList.size());
                yn = propertyValueService.update(propertyValueList.get(i));
                if (yn) {
                    logger.info("产品属性更新成功！");
                } else {
                    logger.warn("产品属性更新失败！事务回滚");
                    jsonObject.put("success", false);
                    throw new RuntimeException();
                }
            }
        }
        if (propertyDeleteList != null && propertyDeleteList.length > 0) {
            logger.info("整合产品子信息-需要删除的产品属性");
            logger.info("共有{}条需要删除的产品属性数据", propertyDeleteList.length);
            yn = propertyValueService.deleteList(propertyDeleteList);
            if (yn) {
                logger.info("产品属性删除成功！");
            } else {
                logger.warn("产品属性删除失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        if (productSingleImageList != null && productSingleImageList.length > 0) {
            logger.info("整合产品子信息-产品预览图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productSingleImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 0)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(product)
                );
            }
            logger.info("共有{}条产品预览图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品预览图片添加成功！");
            } else {
                logger.warn("产品预览图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        if (productDetailsImageList != null && productDetailsImageList.length > 0) {
            logger.info("整合产品子信息-产品详情图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productDetailsImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 1)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(product)
                );
            }
            logger.info("共有{}条产品详情图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品详情图片添加成功！");
            } else {
                logger.warn("产品详情图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        jsonObject.put("success", true);
        jsonObject.put("product_id", product_id);
*/

        return jsonObject.toJSONString();
    }

    //上传产品图片-ajax
    @ResponseBody
    @RequestMapping(value = "admin/uploadProductImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadProductImage(@RequestParam MultipartFile file, @RequestParam String imageType, HttpSession session) {
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名：{}", originalFileName);
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String filePath;
        String fileName = UUID.randomUUID() + extension;
        if ("single".equals(imageType)) {
            filePath = session.getServletContext().getRealPath("/") + "res/images/item/productSinglePicture/" + fileName;
        } else {
            filePath = session.getServletContext().getRealPath("/") + "res/images/item/productDetailsPicture/" + fileName;
        }

        logger.info("文件上传路径：{}", filePath);
        JSONObject object = new JSONObject();
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传完成");
            object.put("success", true);
            object.put("fileName", fileName);
        } catch (IOException e) {
            logger.warn("文件上传失败！");
            e.printStackTrace();
            object.put("success", false);
        }

        return object.toJSONString();
    }
    //按ID删除产品图片并返回最新结果-ajax
    @ResponseBody
    @RequestMapping(value = "admin/productImage/{productImage_id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    public String deleteProductImageById(@PathVariable Integer productImage_id/* 产品图片ID */){
        JSONObject object = new JSONObject();
        logger.info("获取productImage_id为{}的产品图片信息",productImage_id);
        /*ProductImage productImage = productImageService.get(productImage_id);

        logger.info("删除产品图片");
        Boolean yn = productImageService.deleteList(new Integer[]{productImage_id});
        if (yn) {
            logger.info("删除图片成功！");
            object.put("success", true);
        } else {
            logger.warn("删除图片失败！事务回滚");
            object.put("success", false);
            throw new RuntimeException();
        }*/
        return object.toJSONString();
    }

}
