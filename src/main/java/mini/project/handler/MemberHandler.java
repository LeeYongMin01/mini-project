package mini.project.handler;

import java.util.Iterator;
import java.util.List;
import mini.project.domain.Member;
import mini.project.util.Prompt;

public class MemberHandler {
  List<Member> memberList;

  public MemberHandler(List<Member> list) {
    this.memberList = list;
  }

  public void add() {
    System.out.println("[회원 가입]");

    Member member = new Member();
    while (true) {
      int count = 0;
      String id = Prompt.inputString("회원번호 : ");
      for (int i = 0; i < memberList.size(); i++) {
        if (id.equals(memberList.get(i).getId())) {
          count++;
          System.out.println("회원번호 중복입니다. 다시 입력해주세요.");
          break;
        } // end if
      } // end for
      if(count==0) {
        member.setId(id);
        break;
      }
    } // end while

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }
    member.setName(Prompt.inputString("이름 : "));
    member.setAge(Prompt.inputInt("나이 : "));
    member.setGender(Prompt.inputString("성별(남자/여자) : "));
    member.setEmail(Prompt.inputString("이메일 : "));
    member.setPassword(Prompt.inputString("패스워드 : "));
    member.setTel(Prompt.inputString("전화번호 : "));
    member.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

    memberList.add(member);
    System.out.println("회원가입을 축하합니다.");
  }

  public void list() {
    System.out.println("[회원 리스트]");

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member member = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s\n",
          member.getId(),
          member.getName(),
          member.getGender(),
          member.getTel(),
          member.getRegisteredDate());
    }
  }
  public Member findByName(String name) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }

  public void detail() {
    System.out.println("[회원 상세정보]");
    String id = Prompt.inputString("회원번호 : ");
    Member member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("회원번호: %s\n", member.getId());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("나이: %d\n", member.getAge());
    System.out.printf("성별: %s\n", member.getGender());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }

  public void update() {
    System.out.println("[회원정보 수정]");
    String id = Prompt.inputString("회원번호 : ");
    Member member = findById(id);

    if (member == null) {
      System.out.println("존재하지 않는 회원입니다.");
      return;
    }

    String name = Prompt.inputString(
        String.format("이름(%s)? ", member.getName()));
    int age = Prompt.inputInt(
        String.format("나이(%d)? ", member.getAge()));
    String gender = Prompt.inputString(
        String.format("성별(%s)? ", member.getGender()));
    String email = Prompt.inputString(
        String.format("이메일(%s)? ", member.getEmail()));
    String password = Prompt.inputString("암호? ");
    String tel = Prompt.inputString(
        String.format("전화(%s)? ", member.getTel()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
    member.setAge(age);
    member.setGender(gender);
    member.setEmail(email);
    member.setPassword(password);
    member.setTel(tel);

    System.out.println("회원정보를  변경하였습니다.");
  }

  public void delete() {
    System.out.println("[회원 삭제]");
    String id = Prompt.inputString("회원번호 : ");
    int index = indexOf(id);

    if (index == -1) {
      System.out.println("존재하지 않는 회원입니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("취소하였습니다..");
      return;
    }

    memberList.remove(index);
    System.out.println("삭제되었습니다.\n그동안 이용해 주셔서 감사합니다.");
  }

  private Member findById(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }

  private int indexOf(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId() == id) {
        return i;
      }
    }
    return -1;
  }

}
