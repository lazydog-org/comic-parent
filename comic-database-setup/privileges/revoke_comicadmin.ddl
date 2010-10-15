revoke alter,create,delete,drop,index,insert,select,update on comic.* from 'comicadmin';
revoke alter,create,delete,drop,index,insert,select,update on comic.* from 'comicadmin'@'localhost';
flush privileges;
