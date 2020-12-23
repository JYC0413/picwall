# 接口说明

### 上传图片
* /api/uploadImage.do
* 方法: POST

|参数名|内容|
|----|----|
|file|上传的文件|

|Header|内容|
|----|----|
|user-agent|浏览器UserAgent|
|cookie|zzsession=一个随机值;|

* 返回示例
```json
{
  "status": 0,
  "obj": "upload_devtest_1608698620330_f6020f787865.jpg"
}
```


### 获得随机图片文件名
* /api/getRand.do
* 方法: POST

|参数名|内容|
|----|----|
|count|随机的数量|

* 返回示例
```json
{
  "status": 0,
  "message": "",
  "obj": [
    {
      "id": 5,
      "filename": "upload_sessiontest_1608482515713_2c2b25a33aa1.jpg"
    },
    {
      "id": 8,
      "filename": "upload_sessiontest_1608482536281_1eb4e2919fcf.jpg"
    }
  ]
}
```


### 通过文件名获得图片(临时)
* /api/getImage.do
* 方法: GET

|参数名|内容|
|----|----|
|fn|文件名|

* 返回图片