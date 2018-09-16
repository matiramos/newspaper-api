package com.utn.newspaper.bootstrap;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.utn.newspaper.model.Category;
import com.utn.newspaper.model.News;
import com.utn.newspaper.model.Reporter;
import com.utn.newspaper.repository.CategoryRepository;
import com.utn.newspaper.repository.NewsRepository;
import com.utn.newspaper.repository.ReporterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Profile("mock")
@Component
public class DevBootstrap implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final ReporterRepository reporterRepository;

    private final Lorem lorem;

    public DevBootstrap(CategoryRepository categoryRepository, NewsRepository newsRepository, ReporterRepository reporterRepository) {
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
        this.reporterRepository = reporterRepository;
        this.lorem = LoremIpsum.getInstance();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IntStream.rangeClosed(1, 14)
                .forEach(name -> {
                    Reporter r = new Reporter();
                    r.setName(lorem.getName());
                    r.setNationality(lorem.getCountry());
                    r.setBio(lorem.getWords(10, 30));
                    reporterRepository.save(r);
                });

        Stream.of("World", "Politics", "Business", "Science", "Health", "Sports", "Travel", "Tech")
                .forEach(category -> {
                    Category c = new Category();
                    c.setName(category);
                    categoryRepository.save(c);
                });

        IntStream.rangeClosed(1, 150)
                .forEach((i) -> {
                    News n = new News();
                    n.setBody(lorem.getParagraphs(3, 10));
                    n.setDate(randomLocalDate());
                    n.setTitle(lorem.getTitle(5, 10));
                    n.setSubtitle(lorem.getWords(10, 20));
                    n.setReporter(reporterRepository.getOne(ThreadLocalRandom.current().nextLong(1, 15)));
                    n.setCategory(categoryRepository.getOne(ThreadLocalRandom.current().nextLong(15, 23)));
                    newsRepository.save(n);
                });
    }

    private LocalDate randomLocalDate() {
        final LocalDate startDate = LocalDate.of(2000, 1, 1);
        final long start = startDate.toEpochDay();

        final LocalDate endDate = LocalDate.now();
        final long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current()
                .longs(start, end)
                .findAny()
                .getAsLong();

        return LocalDate.ofEpochDay(randomEpochDay);
    }
}
