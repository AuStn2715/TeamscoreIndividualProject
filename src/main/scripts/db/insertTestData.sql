--тестовые данные
--rollback

begin;
truncate table "conferences"."conference" cascade;
truncate table "authors"."author" cascade;

insert into "authors".author (id, firstName, surname, description, organization) VALUES
(1, 'Александр', 'Бабаев', '', 'организация'),
(30, 'не Александр', 'не Бабаев', '', 'организация');



commit;