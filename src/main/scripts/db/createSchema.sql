create schema if not exists "conferences";
create schema if not exists "authors";

drop table if exists "conferences"."conference" CASCADE;
drop table if exists "conferences"."section" CASCADE;
drop table if exists "conferences"."report" CASCADE;
drop table if exists "authors"."author" CASCADE;
drop table if exists "conferences"."report_author" CASCADE;

create table "conferences"."conference" (
	"id" bigserial primary key,
	"title" text not null,
	"event_date" date,
	"event_organizer" text
);

create table "conferences"."section" (
	"id" bigserial primary key,
	"title" text not null,
	"venue" text,
	"moderator" text,
	"start_date_time" timestamp,
    "report_duration_limit" int,
    "conference_id" bigint,
    foreign key ("conference_id")
    		references "conferences"."conference"("id")
    		on delete cascade
);

create table "conferences"."report" (
    "id" bigserial primary key,
    "section_id" bigint,
    "theme" text not null,
    "annotation" varchar(1000),

    foreign key ("section_id")
        		references "conferences"."section"("id")
        		on delete cascade
);

create table "authors"."author" (
    "id" bigserial primary key,
    "surname" text not null,
    "first_name" text not null,
    "organization" text,
    "description" text,
    "contact_info" text
);

create table "conferences"."report_author" (
    "report_id" bigint,
    "author_id" bigint,

    foreign key ("report_id")
    		references "conferences"."report"("id")
    		on delete cascade,
    	foreign key ("author_id")
    		references "authors"."author"("id")
    		on delete cascade,
    	primary key ("report_id", "author_id")
);