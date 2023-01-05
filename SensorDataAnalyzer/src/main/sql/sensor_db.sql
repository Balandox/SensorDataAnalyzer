CREATE TABLE Sensor(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE Measurement(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sensor_id int REFERENCES Sensor(id) ON DELETE SET NULL,
    value real NOT NULL,
    raining boolean NOT NULL,
    time timestamp NOT NULL
);

ALTER TABLE Sensor ADD COLUMN rainy_days int DEFAULT 0;

DROP TABLE Measurement

