SELECT setval('test_item_item_id_seq', max(item_id)) FROM test_item;
SELECT setval('log_id_seq', max(id)) FROM log;
SELECT setval('attachment_id_seq', max(id)) FROM attachment;

SELECT nextval('test_item_item_id_seq') FROM test_item;
SELECT nextval('log_id_seq') FROM log;
SELECT nextval('attachment_id_seq') FROM attachment;