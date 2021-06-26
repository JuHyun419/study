package org.zerock.board.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.QBoard;
import org.zerock.board.entity.QMember;
import org.zerock.board.entity.QReply;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public SearchBoardRepositoryImpl() {
        super(Board.class);

    }

    @Override
    public Board search1() {
        log.info("search1 ...............");

        QBoard qBoard = QBoard.board;

        JPQLQuery<Board> jpqlQuery = from(qBoard);
        jpqlQuery.select(qBoard)
                .where(qBoard.bno.eq(1L));

        log.info("jpqlQuery: \n" + jpqlQuery);

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQuery1 = from(board);
        jpqlQuery1.leftJoin(reply).on(reply.board.eq(board));

        log.info("jpqlQuery1: \n" + jpqlQuery1);

        return null;
    }

    @Override
    public Board search2() {
        log.info("search2 ...............");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);

        jpqlQuery.select(board, member.email, reply.count())
                .leftJoin(member).on(board.member.eq(member))
                .leftJoin(reply).on(reply.board.eq(board))
                .groupBy(board);

        log.info("jpqlQuery: " + jpqlQuery);

        return null;
    }

    @Override
    public Board search3() {
        log.info("search3 ...............");
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.member.eq(member))
                .leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count())
                .groupBy(board);
        log.info("tuple: " + tuple);

        List<Tuple> result = tuple.fetch();
        log.info("result: " + result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage .....");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.member.eq(member))
                .leftJoin(reply).on(reply.board.eq(board));

        // SELECT b, w, count(r) FROM Board b
        // LEFT JOIN b.member w LEFT JOIN Reply r ON r.board = b
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder typeBuilder = new BooleanBuilder();
            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        typeBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        typeBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        typeBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(typeBuilder);
        }

        tuple.where(booleanBuilder).groupBy(board);

        // order by
        Sort sort = pageable.getSort();

        // tuple.orderby(board.bno.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // paging
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        log.info("result: " + result);

        long count = tuple.fetchCount();
        log.info("count: " + count);

        return new PageImpl<>(
                result.stream().map(Tuple::toArray).collect(Collectors.toList()),
                pageable,
                count
        );
    }

}
