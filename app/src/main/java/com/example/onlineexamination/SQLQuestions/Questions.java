package com.example.onlineexamination.SQLQuestions;

public class Questions {

  int id;
  String Question;
  String OptionA;
  String OptionB;
  String OptionC;
  String OptionD;
  String CorrectOption;
  String coursekey;

  public Questions() {
}

  public Questions(int id, String question, String optionA, String optionB, String optionC, String optionD, String correctOption, String coursekey) {
    this.id = id;
    this.Question = question;
   this.OptionA = optionA;
    this.OptionB = optionB;
    this.OptionC = optionC;
    this.OptionD = optionD;
    this.CorrectOption = correctOption;
    this.coursekey = coursekey;
  }

  public Questions(String question, String optionA, String optionB, String optionC, String optionD, String correctOption, String coursekey) {
    this.Question = question;
    this.OptionA = optionA;
    this.OptionB = optionB;
    this.OptionC = optionC;
    this.OptionD = optionD;
    this.CorrectOption = correctOption;
    this.coursekey = coursekey;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getQuestion() {
    return Question;
  }

  public void setQuestion(String question) {
    Question = question;
  }

  public String getOptionA() {
    return OptionA;
  }

  public void setOptionA(String optionA) {
    OptionA = optionA;
  }

  public String getOptionB() {
    return OptionB;
  }

  public void setOptionB(String optionB) {
    OptionB = optionB;
  }

  public String getOptionC() {
    return OptionC;
  }

  public void setOptionC(String optionC) {
    OptionC = optionC;
  }

  public String getOptionD() {
    return OptionD;
  }

  public void setOptionD(String optionD) {
    OptionD = optionD;
  }

  public String getCorrectOption() {
    return CorrectOption;
  }

  public void setCorrectOption(String correctOption) {
    CorrectOption = correctOption;
  }

  public String getCoursekey() {
    return coursekey;
  }

  public void setCoursekey(String coursekey) {
    this.coursekey = coursekey;
  }
}
