package com.assessment.icinbank.checkbook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckBookService {

    private final CheckBookRepository checkBookRepository;

    public List<CheckBook> getAllCheckBooks(){
        return checkBookRepository.findAllByStatus(false);
    }

    public void grantPrimaryCheckBook(Long userId) {
       CheckBook checkBook =  checkBookRepository.findByUserIdAndType(userId,"PRIMARY");
       checkBook.setCheckBookStatus(true);
       checkBookRepository.save(checkBook);
    }

    public void grantSavingsCheckBook(Long userId){
        CheckBook checkBook = checkBookRepository.findByUserIdAndType(userId,"SAVINGS");
        checkBook.setCheckBookStatus(true);
        checkBookRepository.save(checkBook);
    }


}
