/*
    Member 추가
*/
INSERT INTO member (id, status, nickname, friend_code, original_url, thumbnail_url, created_at, updated_at, is_deleted)
VALUES
    (1, 'ACTIVE', 'nick', 'ABCDEFG', 'keyme.space/original', 'keyme.space.thumbnail', '2023-08-05T16:07:00', '2023-08-05T16:07:00', false),
    (2, 'ACTIVE', 'james', '1234567', 'keyme.space/original', 'keyme.space.thumbnail', '2023-08-05T16:07:00', '2023-08-05T16:07:00', false),
    (3, 'ACTIVE', 'peter', 'A1B2C3D', 'keyme.space/original', 'keyme.space.thumbnail', '2023-08-05T16:07:00', '2023-08-05T16:07:00', false);

INSERT INTO member_oauth (oauth_id, oauth_type, member_id)
VALUES
    (1, 'KAKAO', 1),
    (2, 'APPLE', 2),
    (3, 'KAKAO', 3);

INSERT INTO member_device (member_device_id, member_id, token)
VALUES
    (1, 1, 'token1'),
    (2, 1, 'token2'),
    (3, 1, 'token3');


/*
    Question 추가
*/
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

INSERT INTO `question` (`question_id`, `title`, `keyword`, `question_category_id`, `category_name`, `is_onboarding`)
VALUES
    (1, '님은 아이돌 덕질에 일가견이 있다.', '주간아이돌', 2, 'PASSION', true),
    (2, '님은 관심 있는 사람에게 밥을 먼저 먹자고할 수 있다.', '플러팅 장인', 2, 'PASSION', true),
    (3, '님은 하여자(하남자)다.', '하여자', 3, 'INTELLIGENCE', true),
    (4, '님의 유튜브 알고리즘은 깨끗하다.', '순수한 뇌', 3, 'INTELLIGENCE', true),
    (5, '님은 N번 까지 고백 공격을 해봤을것 같다.', '나무꾼', 4, 'HUMOR', true),
    (6, '님은 번호를 따여봤을 것 같다.', '김태희', 5, 'BODY', true),
    (7, '님은 나이보다 어려보인다.', '동안', 5, 'BODY', true),
    (8, '님은 주변의 변화를 빠르게 알아차리는 편이다.', '눈썰미', 7, 'SENSE', true),
    (9, '님은 너무 다정해서 유죄인간이다.', '유죄인간', 7, 'SENSE', true),
    (10, '님은 친구 전화를 귀찮아서 안받은 적이 많다.', '귀차니즘', 8, 'PLANNING', true);


/*
    Test 추가
*/
INSERT INTO test (test_id, owner_id, is_onboarding, title, created_at, updated_at)
VALUES
    (1, 1, true, '님은 아이돌 덕질에 일가견이 있다.', '2023-08-10T16:07:00', '2023-08-10T16:07:00'),
    (2, 2, true, '님은 아이돌 덕질에 일가견이 있다.', '2023-08-10T16:07:00', '2023-08-10T16:07:00');
INSERT INTO question_bundle (test_id, question_id)
VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
    (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10);

/*
    Test 결과 추가
*/
INSERT INTO test_result (test_result_id, test_id, solver_id, match_rate, created_at, updated_at)
VALUES
    (1, 1, 1, 100, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (2, 1, null, 80, '2023-08-10T17:37:00', '2023-08-10T17:37:00');
--     (3, 2, 2, 80, '2023-08-10T17:37:00', '2023-08-10T17:37:00');
INSERT INTO question_solved(question_solved_id, test_result_id, question_id, question_owner_id, score, created_at, updated_at)
VALUES
    (1, 1, 1, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (2, 1, 2, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (3, 1, 3, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (4, 1, 4, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (5, 1, 5, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (6, 1, 6, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (7, 1, 7, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (8, 1, 8, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (9, 1, 9, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00'),
    (10, 1, 10, 1, 4, '2023-08-10T17:07:00', '2023-08-10T17:07:00');
INSERT INTO question_solved(question_solved_id, test_result_id, question_id, question_owner_id, score, created_at, updated_at)
VALUES
    (11, 2, 1, 1, 4, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (12, 2, 2, 1, 3, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (13, 2, 3, 1, 4, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (14, 2, 4, 1, 2, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (15, 2, 5, 1, 3, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (16, 2, 6, 1, 3, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (17, 2, 7, 1, 4, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (18, 2, 8, 1, 4, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (19, 2, 9, 1, 1, '2023-08-11T17:07:00', '2023-08-11T17:07:00'),
    (20, 2, 10, 1, 2, '2023-08-11T17:07:00', '2023-08-11T17:07:00');
-- INSERT INTO question_solved(question_solved_id, test_result_id, question_id, question_owner_id, score, created_at, updated_at)
-- VALUES
--     (21, 3, 1, 2, 2, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (22, 3, 2, 2, 1, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (23, 3, 3, 2, 4, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (24, 3, 4, 2, 3, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (25, 3, 5, 2, 5, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (26, 3, 6, 2, 5, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (27, 3, 7, 2, 2, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (28, 3, 8, 2, 4, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (29, 3, 9, 2, 3, '2023-08-11T17:17:00', '2023-08-11T17:17:00'),
--     (30, 3, 10, 2, 5, '2023-08-11T17:17:00', '2023-08-11T17:17:00');

/*
    Test Result Code 추가
*/
INSERT INTO test_result_code(result_id, code)
VALUES
    (2, 'KW2EDw'),
    (3, 'KW2EDq');


/*
    Statistic 추가
*/
INSERT INTO statistic (id, owner_id, question_id, solver_count, owner_score, solver_avg_score, match_rate, created_at, updated_at)
VALUES
    (1, 1, 1, 3, 5, 1, 20, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (2, 1, 2, 3, 5, 2, 22, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (3, 1, 3, 3, 5, 2, 25, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (4, 1, 4, 3, 5, 3, 33.33, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (5, 1, 5, 3, 5, 4, 50, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (6, 1, 6, 3, 5, 5, 100, '2023-08-05T16:07:00', '2023-08-05T16:07:00');

INSERT INTO statistic (id, owner_id, question_id, solver_count, owner_score, solver_avg_score, match_rate, created_at, updated_at)
VALUES
    (7, 2, 1, 2, 5, 5, 100, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (8, 2, 2, 1, 5, 5, 0, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (9, 2, 3, 1, 5, 5, 0, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (10, 2, 4, 1, 5, 5, 0, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
    (11, 2, 5, 1, 5, 5, 0, '2023-08-05T16:07:00', '2023-08-05T16:07:00');
--     (7, 1, 7, 3, 5, 1, 20, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),
--     (8, 1, 8, 3, 5, 1, 20, '2023-08-05T16:07:00', '2023-08-05T16:07:00'),


