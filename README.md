# PhotoManager
Its demo application for save metaobjects of pictures in relation database(MySQL)
and download hims on web via http requests.

We have 4 methods. 
1)Init for initializations of readies jsons from server
2)Get all photos from our database
3)Get photos by album ID
4)Get photo by local path of photo from our backend

Examples of requests:

1)Init
http://localhost:8080/photo/init

2)GetAllPhotoObjects
http://localhost:8080/photo/all

3)GetAllPhotoObjectsByAlbum
http://localhost:8080/photo/all/1

4)GetPhoto
http://localhost:8080/photo/download?path=D:\\Projects\\my_example_app\\src\\images\\1\\photo2.jpg