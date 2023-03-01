DROP DATABASE IF EXISTS ENSF614PROJECT;
CREATE DATABASE IF NOT EXISTS ENSF614PROJECT;
USE ENSF614PROJECT;

CREATE TABLE IF NOT EXISTS THEATRE
    (theatreId		int        	NOT NULL,
     theatreName	VARCHAR(30)	NOT NULL,
     primary key(theatreId)
     );

CREATE TABLE IF NOT EXISTS MOVIE
    (movieId		int				NOT NULL,
     title			VARCHAR(30)     NOT NULL,
     rating			VARCHAR(30)		NOT NULL,
     releasedate	date        	NOT NULL,
     PRIMARY KEY (movieId)
	);

CREATE TABLE IF NOT EXISTS SHOWTIME
    (showtimeId		int         NOT NULL,
     movieId        int         NOT NULL,
     theatreId      int         NOT NULL,
     showtime       datetime    NOT NULL,
     primary key(showtimeId),
     foreign key(movieId) references MOVIE(movieId),
     foreign key(theatreId) references THEATRE(theatreId)
     );

CREATE TABLE IF NOT EXISTS SEAT
    (seatId     int		NOT NULL AUTO_INCREMENT,
     seatRow    int     NOT NULL,
     seatNum	int     NOT NULL,
--      ticketId	int 	NOT NULL,
     primary key (seatId));    
     
CREATE TABLE IF NOT EXISTS TICKET
    (ticketId       int         NOT NULL AUTO_INCREMENT,
     showtimeId 	int         NOT NULL,
     price          double      NOT NULL,
     seatId			int			NOT NULL,
     primary key(ticketId),
     foreign key(showtimeId) references SHOWTIME(showtimeId),
     foreign key(seatId) references SEAT(seatId)
     );
     
     
-- although they dont have it, keep it 
CREATE TABLE IF NOT EXISTS SALE(
	saleId		int 	NOT NULL,
    ticketId	int		NOT NULL,
    primary key(saleId,ticketId),
    foreign key(ticketId) references TICKET(ticketId)
);

CREATE TABLE IF NOT EXISTS PAYMENT
    (paymentId      int     NOT NULL AUTO_INCREMENT,
     saleId			int 	NOT NULL,
     ccId			int		NOT NULL,
     primary key(paymentId),
     foreign key(saleId) references SALE(saleId)
     );
     
CREATE TABLE IF NOT EXISTS CCINFO(
	 ccId 			int 			NOT NULL,
	 holderName     VARCHAR(255)    NOT NULL,
     cardNumber     varchar(255)	NOT NULL,
     expiry         date            NOT NULL,
     cvv			int				NOT NULL,
     primary key(ccId)
);

CREATE TABLE IF NOT EXISTS REGISTERED_USER(
	 userName		VARCHAR(225)	NOT NULL,
     pwrd			VARCHAR(255)	NOT NULL,
     address		VARCHAR(225)	NOT NULL,
     email			VARCHAR(225)	NOT NULL,
     ccId			INT				NOT NULL,
     lastPaid       date            NOT NULL, 
     primary key(userName),
     foreign key (ccId) references CCINFO(ccId)
);


INSERT INTO THEATRE (theatreId,theatreName) VALUES
(1,"Crowfoot Crossing"),
(2,"Chinook"),
(3,"Eau Claire Market");


INSERT INTO MOVIE (movieId,title,rating,releasedate) VALUES
(1,"Black Adam","5","2022-12-01"),
(2,"The Lion King","8","2021-12-02"),
('3', 'The Godfather: Part II', '9', '1974-12-20'),
('4', 'The Dark Knight', '9', '2008-07-16'),
('5', '12 Angry Men', '8', '1957-04-10'),
('6', 'Pulp Fiction', '8', '1994-09-10');

INSERT INTO SHOWTIME (showtimeId,movieId,theatreId,showtime) VALUES
(1, 1, 1,"2021-12-08 09:00:00"),
(2, 2, 2,"2021-12-08 21:00:00"),
(3, 1, 3,"2021-11-09 09:00:00"),
(4, 1, 3,"2021-10-08 09:00:00"),
(5, 2, 2,"2021-09-08 09:00:00"),
(6, 6, 1, '2022-12-03 17:00:00'),
(7, 4, 2, '2022-12-03 21:00:00'),
(8, 3, 1, '2022-12-03 21:00:00'),
(9, 5, 2, '2022-12-03 21:00:00'),
(10, 5, 3, '2022-12-03 21:00:00');

INSERT INTO SEAT (seatId,seatRow,seatNum) VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,2,1),
(7,2,2),
(8,2,3),
(9,2,4),
(10,2,5),
(11,3,1),
(12,3,2),
(13,3,3),
(14,3,4),
(15,3,5),
(16,4,1),
(17,4,2),
(18,4,3),
(19,4,4),
(20,4,5);


INSERT INTO TICKET (ticketId,showtimeId,price,seatId) VALUES
(1,1,10,1),
(2,1,10,2),
(3,2,10,1),
(4,2,10,2),
(5,2,10,3);

INSERT INTO CCINFO(ccId,holderName,cardNumber,expiry,cvv) VALUES
(1,"Lisa Smith", "1234567812345678", "2022-01-01", 123),
(2,"John Apple", "5598855665566810", "2023-01-01", 567);

INSERT INTO SALE (saleId, ticketId) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(2, 5);

INSERT INTO PAYMENT (paymentId, saleId, ccId) VALUES
(1, 1, 1),
(2, 2, 2);

INSERT INTO REGISTERED_USER (userName, pwrd, address, email, ccId, lastPaid) VALUES
("Lisa_S.123", "pass123", "P. Sherman 42 Wallaby Way", "email@service.com",1, "2022-01-01"),
("Johnny.A", "password420", "123 House Drive SE", "emailAddress@server.com",2, "2021-11-06");


