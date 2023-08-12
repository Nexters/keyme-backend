package com.nexters.keyme.test.domain.helper;

import com.nexters.keyme.test.domain.model.TestResultCode;
import com.nexters.keyme.test.domain.repository.TestResultCodeRepository;
import com.nexters.keyme.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class TestResultCodeProvider {
    private final TestResultCodeRepository testResultCodeRepository;

    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int LENGTH = 6;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String createResultCode(Long resultId) {
        // TODO : 해당 result 1일 뒤 만료
        String generatedCode = null;

        do {
            String code = generateCode();
            TestResultCode testResultCode = testResultCodeRepository.findById(code).orElse(null);

            if (testResultCode == null) {
                testResultCodeRepository.save(new TestResultCode(code, resultId));
                generatedCode = code;
            }
        } while (generatedCode == null);

        return generatedCode;
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }
}
