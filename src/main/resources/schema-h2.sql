CREATE TABLE Recipe
(
    ID          UUID          DEFAULT RANDOM_UUID() PRIMARY KEY,
    NAME        VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(1024),
    IMAGEURL    VARCHAR(1024) DEFAULT 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
    RECIPE      VARCHAR(4000),
    POSITION    INT,
    DIFFICULTY  VARCHAR(40)
);