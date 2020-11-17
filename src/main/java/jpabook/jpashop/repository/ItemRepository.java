package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    
    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        } else {
            // 왠만하면 사용 X 단순한 경우에만 사용 (merge 사용시 세팅이 되지않은 파라미터 컬럼에 null값이 들어간다.)
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
