package br.com.inmetrics.desafiokernel.util;

import br.com.inmetrics.desafiokernel.vo.S3ObjectVO;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.support.PagedListHolder;

import java.util.Iterator;
import java.util.List;

public class PaginationS3Objects<T> implements Iterable<T> {

    private final List<T> list;
    private int currentPage;
    public static final Integer PAGING_SIZE = 10;

    public PaginationS3Objects(List<T> list, int currentPage) {
        this.list = list;
        this.currentPage = currentPage;
    }

    private List<T> getItems() {
        return list.subList(PAGING_SIZE * (currentPage-1), PAGING_SIZE * currentPage);
    }

    public List<S3ObjectVO> pagedList(int page){
        PagedListHolder pagedListHolder = new PagedListHolder(list);
        pagedListHolder.setPageSize(PAGING_SIZE);
        pagedListHolder.setPage(page);
        return pagedListHolder.getPageList();
    }

    @Override
    public Iterator<T> iterator() {
        return getItems().iterator();
    }
}
