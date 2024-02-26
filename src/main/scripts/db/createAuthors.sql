create schema "authors";

create table "authors"."author" (
	"id" bigserial primary key,
	"firstName" text,
	"surname" text,
	"description" text,
	"organization" text
);