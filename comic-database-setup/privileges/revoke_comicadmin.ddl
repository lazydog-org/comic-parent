revoke alter,create,delete,drop,index,insert,select,update on comic_collection.* from 'comicadmin';
revoke alter,create,delete,drop,index,insert,select,update on comic_collection.* from 'comicadmin'@'localhost';
flush privileges;
