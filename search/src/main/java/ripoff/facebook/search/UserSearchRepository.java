package ripoff.facebook.search;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Repository
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {

    interface UserSearchInternal {
        List<User> search(String query);
    }

    @RequiredArgsConstructor
    @Repository
    class UserSearchInternalImplementation implements UserSearchInternal {

        private final ElasticsearchOperations elasticsearchTemplate;

        @Override
        public List<User> search(String query) {
            NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                    .withQuery(
                            multiMatchQuery(query)
                                    .field("name")
                                    .field("lastName")
                                    .fuzziness(Fuzziness.TWO)
                                    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                    )
                    .build();
            SearchHits<User> users = elasticsearchTemplate.search(nativeSearchQuery, User.class, IndexCoordinates.of("userindex"));
            return users
                    .stream()
                    .map(SearchHit::getContent)
                    .collect(Collectors.toList());
        }
    }
}
