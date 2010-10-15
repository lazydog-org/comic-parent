revoke delete,insert,select,update on comic.* from 'comicuser';
revoke delete,insert,select,update on comic.* from 'comicuser'@'localhost';
flush privileges;
