-- is_onboarding이 true인 경우 (1~10번)
INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 거지방에서도 살아남을 사람이다', '절약왕', 'MONEY', true),
    ('님은 술자리에서 가장 늦게 일어나는 타입이다', '술고래', 'PASSION', true),
    ('님은 별별 TMI를 다 아는 인간 나무위키다', '인간나무위키', 'INTELLIGENCE', true),
    ('님은 친구들 사이에서 개그맨이다', '개그맨', 'HUMOR', true),
    ('님은 걸어다니는 종합병원이다', '개복치', 'BODY', true),
    ('님은 밥은 살려고 먹는다', '소식좌', 'FOOD', true),
    ('님은 주변의 변화를 빠르게 알아차리는 편이다', '눈썰미', 'SENSE', true),
    ('님은 하고 싶은 것이 생기면 바로 실행에 옮긴다', '진행시켜', 'PLANNING', true),
    ('님은 맑은 햇살의 낮보다는 새벽 감성을 더 좋아한다', '새벽감성', 'SENSIBILITY', true),
    ('님은 어떠한 것이든 솔직하게 표현하는 편이다', '돌직구', 'RELATIONSHIPS', true),
    ('님은 관심 있는 사람에게 밥을 먼저 먹자고할 수 있다', '플러팅 장인', 'AFFECTION', true);

-- is_onboarding이 false인 경우
INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 돈관리를 잘한다', '돈관리 마스터', 'MONEY', false),
    ('님은 얘기를 하다가 갑자기 멍을 때린다', '무념무상', 'PASSION', false),
    ('님은 길에서 도를 아십니까와 마주쳤을때 무시하고 지나간다', '마이웨이', 'PASSION', false),
    ('님은 불의를 보면 참지 못한다', '정열맨', 'PASSION', false),
    ('님은 쌓여있는 메세지를 확인하지 않는다', '읽씹왕', 'PASSION', false),
    ('님은 항상 적극적으로 나서는 편이다', '적극왕', 'PASSION', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 돈관리를 잘한다', '돈관리 마스터', 'MONEY', false);


DELETE FROM `question`;