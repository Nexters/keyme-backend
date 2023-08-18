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
    ('님은 모임에서 주도적인 역할을 할 때가 많다', '사회자', 'PASSION', false),
    ('님은 평소 집중력이 좋다', '집중력', 'PASSION', false),
    ('님은 내가 좋아하는 것들 이외에는 관심이 없다', '마이웨이', 'PASSION', false),
    ('님은 일처리에 만족할 때까지 시간을 많이 쓰는 편이다', '완벽주의', 'PASSION', false),
    ('님은 정말 몰두하는 일이 한 가지 이상이 있다', '덕후체질', 'PASSION', false),
    ('님은 동료들과 경쟁을 하는 것을 좋아한다', '이기자', 'PASSION', false),
    ('님은 작은 게임도 목숨 걸고 이기려고 한다', '승부사', 'PASSION', false),
    ('님은 새로운 상황을 만났을때 걱정이 많은 편이다', '걱정왕', 'PASSION', false),
    ('님은 무섭거나 스릴있는 걸 잘한다', '담력', 'PASSION', false),
    ('님은 평소 자신이 말하는 것을 행동으로 잘 옮긴다', '실천력', 'PASSION', false),
    ('님은 길고양이를 위해 항상 츄르를 준비하고 있다', '동물좋아', 'PASSION', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 똘끼가 있다', '똘끼', 'HUMOR', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 노래를 잘 부른다', '나가수', 'BODY', false),
    ('님은 주사가 얌전하다', '술선비', 'BODY', false),
    ('님은 술을 잘 먹는다', '술부심', 'BODY', false),
    ('님은 커피없이는 못 살 것 같다', '카페인 중독자', 'BODY', false);


INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 항상 먹을 것을 챙겨서 친구들에게 나눠주고는 한다', '혜자', 'FOOD', false),
    ('님은 매운걸 잘 먹는다', '맵부심', 'FOOD', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 남들이 보지 못한 걸 잘 보는 사람이다', '통찰력', 'SENSE', false),
    ('님은 새로운 곳을 가면 잘 적응한다', '적응력', 'SENSE', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 시험준비를 할 때 꾸준히 공부하기보단 벼락치기한다', '강심장', 'PLANNING', false),
    ('님은 모르는 장소에 갈 때 로드뷰까지 꼼꼼히 살펴보는 타입이다', '꼼꼼왕', 'PLANNING', false),
    ('님은 약속이 있으면 꼭 늦는다', '코리안타임', 'PLANNING', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 논리적이지 못한 말을 들으면 참을 수 없다', '논리왕', 'SENSIBILITY', false),
    ('님은 남 몰래 우는 경우가 많다', '쿠크다스', 'SENSIBILITY', false),
    ('님은 화를 잘 내는 편이다', '다혈질', 'SENSIBILITY', false),
    ('님은 드라마에서 주인공에게 과몰입한다', '과몰입러', 'SENSIBILITY', false),
    ('님은 가성비보다는 최고의 제품을 산다', '가성비', 'SENSIBILITY', false),
    ('님은 비오는 날에 창 밖을 보며 여유를 즐길 것 같다', '감성파', 'SENSIBILITY', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 친구와 노는 중 근처에 있는 지인을 부를 수 있다', '인싸', 'RELATIONSHIPS', false),
    ('님은 하루뿐인 휴가를 집에서 보낸다', '이불과한몸', 'RELATIONSHIPS', false),
    ('님은 약속이 있는데 갑자기 비가 오면 다음에 본다', '집순이', 'RELATIONSHIPS', false),
    ('님은 혼자만의 시간을 가질 때 행복감을 느끼는 편이다', '독립적', 'RELATIONSHIPS', false),
    ('님은 분위기를 잘 띄운다', '광대력', 'RELATIONSHIPS', false),
    ('님은 약속이 가득 차서 친구한테 한참 뒤에 보자고 한 적이 있다', '핵인싸', 'RELATIONSHIPS', false),
    ('님은 뒤끝이 없다', '쿨워터', 'RELATIONSHIPS', false),
    ('님은 다른 사람 부탁을 다 들어주다가 자기 일을 못한 적이 있다', '봇짐러', 'RELATIONSHIPS', false),
    ('님은 혼자 고민하기보다 동료들과 함께 문제를 해결한다', '협력왕', 'RELATIONSHIPS', false),
    ('님은 친구가 100만원을 빌려달라고 하면 빌려줄 것 같다', '의리의리', 'RELATIONSHIPS', false),
    ('님은 첫인상과 후인상이 정말 다른 사람이다', '반전매력', 'RELATIONSHIPS', false),
    ('님은 표정관리를 잘한다', '포커페이스', 'RELATIONSHIPS', false),
    ('님은 친구 생일 선물 고르는 데에 시간을 많이 쓴다', '챙김러', 'RELATIONSHIPS', false);

INSERT INTO `question` (`title`, `keyword`, `category_name`, `is_onboarding`)
VALUES
    ('님은 인스타 피드에 여자 인플루언서가 많이 나오는 편이다', '여미새', 'AFFECTION', false),
    ('님은 연애 할 때 질투심이 많은 편이다', '질투의 화신', 'AFFECTION', false);