INSERT INTO groceries
(ID,
 PARENTID,
 ISCATEGORY,
 NAME,
 QUANTITY,
 PRICE)
VALUES
  ('1150b23a-e004-44b2-aa6f-aec18ae69d41',
   '00000000-0000-0000-0000-000000000000',
   0,
   'cherry',
   100500,
   222.55);
INSERT INTO grocerylist
(PK,
 ID,
 GROCERYID,
 QUANTITY)
VALUES
  (1,
   'c44b23be-8e24-4096-911d-623d2794b716',
   '1150b23a-e004-44b2-aa6f-aec18ae69d41',
   100);
INSERT INTO orders
(ID,
 USERID,
 STATUSID,
 PRICE,
 DATETIME,
 GROCERYLISTID,
 ADDRESS)
VALUES
  ('0101b824-da41-481d-a0c4-76a4dbabbc3c',
   '839356a3-9a4a-4764-a01e-859ba979ab25',
   'c24be575-187f-4d41-82ee-ff874764b829',
   100.25,
   '2016-12-3',
   'c44b23be-8e24-4096-911d-623d2794b716',
   'U-U');
INSERT INTO orderupdates
(ID,
 STATUS)
VALUES
  ('1c8d12cf-6b0a-4168-ae2a-cb416cf30da5',
   'отменен');
INSERT INTO orderupdates
(ID,
 STATUS)
VALUES
  ('b1ed9007-e220-4a2b-8a81-eee2ebc8e277',
   'исполнен');
INSERT INTO orderupdates
(ID,
 STATUS)
VALUES
  ('c24be575-187f-4d41-82ee-ff874764b829',
   'принят');
INSERT INTO roles_sec
(ID,
 ROLENAME)
VALUES
  ('2597d800-eec0-4347-84fd-324d1cb8e0bb',
   'ROLE_ADMIN');
INSERT INTO roles_sec
(ID,
 ROLENAME)
VALUES
  ('81446dc5-bd04-4d41-bd72-7405effb4716',
   'ROLE_USER');
INSERT INTO users_sec
(ID,
 EMAIL,
 PASSWORD,
 STATUS,
 NAME,
 LASTNAME,
 SURNAME,
 ADDRESS,
 PHONE)
VALUES
  ('839356a3-9a4a-4764-a01e-859ba979ab25',
   'userModel@mail.ru',
   '$2a$10$noOo/7kXbsf3VClbMogKE.iq8totYmERfhKntGq6sGKpCR30zOH16',
   'ACTIVE',
   'Сергей',
   'Иванов',
   'Викторович',
   '670024, Республика Бурятия, г.Улан-Удэ, ул.Пушкина, д.10, кв.27',
   '999999');
INSERT INTO usersandroles
(USER_ID,
 ROLE_ID)
VALUES
 ('839356a3-9a4a-4764-a01e-859ba979ab25',
  '2597d800-eec0-4347-84fd-324d1cb8e0bb');