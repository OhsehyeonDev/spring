package test;

public class TestProject {
	private articleDao dao;
	private String dir;
	public void test() {
		System.out.println("�׽�Ʈ ����");
		articleDao.insert(dao + "�� insert ��.");
	   }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setDao(MariadbDao mariadbDao) {
        this.dao = mariadbDao;
    }
}