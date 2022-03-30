package br.com.letscode.shop.produto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetLimitPageable implements Pageable {

    private Integer offset;
    private Integer limit;

    public OffsetLimitPageable(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public int getPageNumber() {
        return offset/limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPageable(offset+getPageSize(), getPageSize());
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPageable(0, getPageSize());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return offset >limit;
    }
}
