INSERT INTO question_category (`question_category_id`, `name`, `color`, `icon_url`)
VALUES
    (1, '돈관리', '568049', 'https://d2z2a95epq6bmg.cloudfront.net/icon/money.png'),
    (2, '열정', 'F37952', 'https://d2z2a95epq6bmg.cloudfront.net/icon/passion.png'),
    (3, '지능', 'D6EC63', 'https://d2z2a95epq6bmg.cloudfront.net/icon/intelligence.png'),
    (4, '유머', '643FFF', 'https://d2z2a95epq6bmg.cloudfront.net/icon/humor.png'),
    (5, '신체', 'EEAFB1', 'https://d2z2a95epq6bmg.cloudfront.net/icon/body.png'),
    (6, '먹거리', 'A74850', 'https://d2z2a95epq6bmg.cloudfront.net/icon/food.png'),
    (7, '센스', 'A9DBC3', 'https://d2z2a95epq6bmg.cloudfront.net/icon/sense.png'),
    (8, '즉흥/계획', 'BF36FE', 'https://d2z2a95epq6bmg.cloudfront.net/icon/planning.png'),
    (9, '감성/이성', '89B5F6', 'https://d2z2a95epq6bmg.cloudfront.net/icon/sensibility.png'),
    (10, '관계', '905CFF', 'https://d2z2a95epq6bmg.cloudfront.net/icon/relationships.png'),
    (11, '애정', 'FB5563', 'https://d2z2a95epq6bmg.cloudfront.net/icon/affection.png');



# Question Column Migration(category_name -> question_category_id)
CREATE TABLE question_category_name_mapping (
   question_category_id INT PRIMARY KEY,
   english VARCHAR(255),
   korean VARCHAR(255)
);

INSERT INTO question_category_name_mapping (question_category_id, english, korean)
VALUES
    (1, 'MONEY', '돈관리'),
    (2, 'PASSION', '열정'),
    (3, 'INTELLIGENCE', '지능'),
    (4, 'HUMOR', '유머'),
    (5, 'BODY', '신체'),
    (6, 'FOOD', '먹거리'),
    (7, 'SENSE', '센스'),
    (8, 'PLANNING', '즉흥/계획'),
    (9, 'SENSIBILITY', '감성/이성'),
    (10, 'RELATIONSHIPS', '관계'),
    (11, 'AFFECTION', '애정');

UPDATE question q
JOIN question_category_name_mapping qm ON q.category_name = qm.english
SET q.question_category_id = qm.question_category_id;

DROP TABLE question_category_name_mapping;