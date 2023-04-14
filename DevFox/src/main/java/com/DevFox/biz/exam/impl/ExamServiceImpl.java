package com.DevFox.biz.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DevFox.biz.exam.ExamService;
import com.DevFox.biz.exam.ExamVO;
import com.DevFox.biz.exam.ExamService;
import com.DevFox.biz.exam.ExamVO;
import com.DevFox.biz.exam.impl.ExamDAO;
import com.DevFox.biz.common.Log4jAdvice;

@Service("examService")
public class ExamServiceImpl implements ExamService {
private Log4jAdvice log;
@Autowired
private ExamDAO examDAO;
public ExamServiceImpl() {
    log = new Log4jAdvice();
}

@Override
public void insertExam(ExamVO vo) {
    examDAO.insertExam(vo);
    log.printLogging();
}

@Override
public void updateExam(ExamVO vo) {
    examDAO.updateExam(vo);
    log.printLogging();
}

@Override
public void updateCnt(ExamVO vo) {
    examDAO.updateCnt(vo);
    log.printLogging();
}

@Override
public void deleteExam(ExamVO vo) {
    examDAO.deleteExam(vo);
    log.printLogging();
}

@Override
public ExamVO getExam(ExamVO vo) {
    log.printLogging();
    return examDAO.getExam(vo);
}

public ExamVO getExam(int i) {
    log.printLogging();
    return examDAO.getExam(i);
}

@Override
public List<ExamVO> getExamList(boolean type) {
    log.printLogging();
    return examDAO.getExamList(type);
}

@Override
public void updateFav(ExamVO vo) {
    examDAO.updateFav(vo);
    log.printLogging();
}
}
