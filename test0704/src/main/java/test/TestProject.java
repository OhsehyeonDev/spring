package test;

public class TestProject {
	private articleDao dao;
	private String dir;
	public void test() {
		System.out.println("테스트 시작");
		articleDao.insert(dao + "로 insert 됨.");
	   }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setDao(MariadbDao mariadbDao) {
        this.dao = mariadbDao;
    }
}