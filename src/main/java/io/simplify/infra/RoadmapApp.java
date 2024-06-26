package io.simplify.infra;

import io.simplify.core.roadmaps.domain.Recommendation;
import io.simplify.core.roadmaps.domain.Roadmap;
import io.simplify.core.roadmaps.domain.Step;
import io.simplify.core.roadmaps.port.out.RoadmapsRepository;
import org.assertj.core.api.Condition;
// import org.junit.jupiter.api.Test;

import java.util.List;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.atIndex;


public class RoadmapApp {
    private final static Roadmap roadmap = new Roadmap("Rich Hickey",
            List.of(new Step("https://www.youtube.com/watch?v=SxdOUGdseq4")));

    class StubRepository implements RoadmapsRepository {

        @Override
        public List<Roadmap> findAllRoadmaps() {
            return List.of(roadmap);
        }

    }

    // void mandatory() {
    //     RoadmapsClassifierByMentor roadmapsClassifierByMentor = new RoadmapsClassifierByMentor(new StubRepository());
    //     List<Roadmap> mandatory = roadmapsClassifierByMentor.mandatory();
    //     assertThat(mandatory).hasSize(1);
    //     assertThat(mandatory).has(recommendation(), atIndex(0));
    // }

    // void optional() {
    //     RoadmapsClassifierByMentor roadmapsClassifierByMentor = new RoadmapsClassifierByMentor(new StubRepository());
    //     List<Roadmap> optional = roadmapsClassifierByMentor.optional();
    //     assertThat(optional).isEmpty();

    // }

    private Condition<Roadmap> recommendation() {
        return new Condition<>(
                m -> m.getRecommendation().equals(Recommendation.MANDATORY),
                "All rich hickey talks are a must see");
    }

    public static void main(String[] args) {
        RoadmapsClassifierByMentor roadmapsClassifierByMentor = new RoadmapsClassifierByMentor(new StubRepository());
        List<Roadmap> mandatory = roadmapsClassifierByMentor.mandatory();
        for (Roadmap rm : mandatory)
            System.out.println(rm);
    }

}
