
CREATE DATABASE demo;

USE demo;

CREATE TABLE user (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL

);

CREATE TABLE products (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    type character varying(100) NOT NULL,
    release_date character varying(20),
    insert_date character varying(20),
    views integer NOT NULL,
    abbreviation character varying(100) NOT NULL

);

CREATE TABLE category (
    id bigint NOT NULL,
    type character varying(100) NOT NULL,
    haslength boolean DEFAULT false,
    length character varying(20)

);