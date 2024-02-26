--все сущносностные классы конференции представлены здесь
create schema "conferences";

create table "conferences"."conference" (
	"id" bigserial primary key,
	"title" text,
    "eventDate" timestamp default now(),
    "eventOrganizer" text,
);

create table "conferences"."section" (
    "id" bigserial primary key,
    "title" text,
    "venue" text,
    "moderator" text,
    "startDateTime" timestamp,
    "reportDurationLimit" int,

    foreign key ("conference_id")
    		references "conferences"."conference"("id")
    		on delete restrict
    -- primary key ("id", "conference_id")
    --возможно было бы удобно сделать ключ составным т.к. секции тесно связаны с конф-ми
);

create table "conferences"."report" (
    "id" bigserial primary key,
	"section_id" bigint,
	"theme" text,
	"annotation" varchar(1000),

	foreign key ("section_id")
		references "conferences"."section"("id")
		on delete restrict
);

--только для разбиения связи "многие ко многим"
create table "conferences"."report_authors" (
	"report_id" bigint,
	"author_id" bigint,

	foreign key ("report_id")
		references "conferences"."report"("id")
		on delete restrict,
	foreign key ("authors_id")
		references "authors"."author"("id")
		on delete restrict,
	primary key ("report_id", "author_id")
);