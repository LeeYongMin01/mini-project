package mini.project.handler;

import java.util.List;
import mini.project.domain.Rent;
import mini.project.util.Prompt;
public class RentHandler {

  List<Rent> rentList;
  MemberHandler memberHandler;
  BookHandler bookHandler;
  Rent rent;

  public RentHandler(List<Rent> list, MemberHandler memberHandler, BookHandler bookHandler) {
    this.rentList = list;
    this.memberHandler = memberHandler;
  }

  //도서 번호 ->
  public void rent() {
    System.out.println("[도서 대여]");

    Object object = new Object();

    while(true) {
      int no = Integer.((Prompt.inputString("도서 번호: "));

      if(no == null) {

      }
    }





  }

  public void searchRentAble() {
    System.out.println("[대여 가능 도서목록]");
    bookHandler.findByBookInfo("ok");
  }

  public void rentImpossible() {
    System.out.println("[대여중 도서목록]");
    bookHandler.findByBookInfo("no");

  }
}
