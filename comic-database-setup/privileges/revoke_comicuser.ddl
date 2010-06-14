revoke delete,insert,select,update on comic_collection.* from 'comicuser';
revoke delete,insert,select,update on comic_collection.* from 'comicuser'@'localhost';
flush privileges;
