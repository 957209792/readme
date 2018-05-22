package org.mail.common;

/**
 * 查询分页类
 * @author VIC
 *
 */
public class QueryPaging {

	// 分页操作(首页：first,尾页:last,上一页:up,下一页:nx)
	private String operate;
	// 当前页码(默认为1)
	private int page = 1;
	// 每页显示数量(默认为8)
	private int size = 5;
	// 总行数
	private int sumLine;
	// 总页数
	private int sumPage;
	// 查询关键字
	private String keywords;
	
	// 查询开始日期(在按日期范围查询使用)
	private long startTime;
	// 查询接收日期(在按日期范围查询使用)
	private long endTime;
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSumLine() {
		return sumLine;
	}
	public void setSumLine(int sumLine) {
		this.sumLine = sumLine;
	}
	public int getSumPage() {
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	private QueryPaging(){}
	public static QueryPaging getInstance() {
		return new QueryPaging();
	}
	
	/**
	 * 计算总页数
	 * @return
	 */
	public int calculationSumPage() {
		sumPage = sumLine / size;
		int temp = sumLine % size;
		if(temp > 0){
			sumPage+=1;
		}
		return sumPage;
	}
	
	/**
	 * 计算页码
	 * @return
	 */
	public int calculationPage() {
		
		if("first".equalsIgnoreCase(operate)){
			page = 1;
		}else if ("up".equalsIgnoreCase(operate)) {
			// 如果是first，首先判断当前页面是否小于等于0
			// 如果小于等于0则翻到最后一页，否则翻到上一页
			page-=1;
			page = page <= 0 ? sumPage : page; 
		}else if ("nx".equalsIgnoreCase(operate)) {
			// 如果是nx，首先判断当前页面是否大于等于最大页数
			// 如果大于等于最大页数则翻到第一页，否则翻到下一页
			page+=1;
			page = page > sumPage ? 1 : page;
		}else if ("last".equalsIgnoreCase(operate)) {
			page = sumPage;
		}else {
			// 其他扩展
		}
		return page;
	}
}
