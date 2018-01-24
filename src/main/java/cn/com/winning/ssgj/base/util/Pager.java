package cn.com.winning.ssgj.base.util;

import java.io.Serializable;
import org.apache.commons.validator.GenericValidator;

/**
 * @author Jiang,JiaYong
 * @version 2014-03-1
 */
public class Pager implements Serializable {
    private static final long serialVersionUID = -4853649627933466931L;

    /**
     * @val 请求的每页记录数据
     */
    private Integer pageSize = new Integer(10);

    /**
     * @val 请求的第几页，页码
     */
    private String page;

    private Integer rows;

    private Long recordCount = new Long(-1L);

    private Integer firstRow = new Integer(0);

    private Integer rowCount = new Integer(10);

    private int currentPage = 1;

    private int pageCount = 1;

    private int firstPage = 1;

    private int priviousPage = 1;

    private int nextPage = 1;

    private int lastPage = 1;

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public Integer getFirstRow() {
        return this.firstRow;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * @val 请求的每页记录数据
     */
    public Integer getPageSize() {
        return this.pageSize;
    }

    public int getPriviousPage() {
        return this.priviousPage;
    }

    public Long getRecordCount() {
        return this.recordCount;
    }

    /**
     * @val 请求的第几页，页码
     */
    public String getPage() {
        return GenericValidator.isLong(this.page) ? this.page : "1";
    }

    public Integer getRowCount() {
        return this.rowCount;
    }

    /**
     * @val 初始化分页相应的查询条件
     */
    public void init(Long recordCount, Integer pageSize, String pageNumber) {
        int iRecordCount = recordCount.intValue();
        int iPageSize = pageSize.intValue();
        int iRequestPage = Integer.parseInt(pageNumber);

        if (iRecordCount == -1) {
            return;
        }

        this.recordCount = recordCount;
        this.pageSize = pageSize;

        this.pageCount = (int) Math.ceil((iRecordCount + iPageSize - 1) / iPageSize);
        if (iRequestPage > this.pageCount)
            iRequestPage = this.pageCount;
        else if (iRequestPage < 1) {
            iRequestPage = 1;
        }
        this.currentPage = iRequestPage;

        this.currentPage = iRequestPage;
        this.priviousPage = (this.currentPage - 1);
        this.nextPage = (this.currentPage + 1);
        this.lastPage = this.pageCount;
        this.firstRow = new Integer(iPageSize * (this.currentPage - 1));
        this.rowCount = this.pageSize;
    }

    /**
     * @val 请求的每页记录数据
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @val 请求的第几页，页码
     */
    public void setPage(String pageNumber) {
        this.page = pageNumber;
    }

    public Integer getRows() {
        return this.rows == null ? 10 : this.rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

}