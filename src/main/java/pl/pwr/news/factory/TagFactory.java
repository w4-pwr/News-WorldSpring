package pl.pwr.news.factory;

import com.google.common.base.CharMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pwr.news.converter.ValueConverter;
import pl.pwr.news.model.category.Category;
import pl.pwr.news.model.tag.Tag;
import pl.pwr.news.service.tag.TagService;

import java.util.List;

import static org.apache.commons.lang.StringUtils.deleteWhitespace;

/**
 * Created by jf on 4/1/16.
 */
@Component
public class TagFactory {

    @Autowired
    TagService tagService;

    public Tag getInstance(String name, List<Category> categories) {
        Tag existingTag = tagService.findByName(name);
        if (existingTag != null) {
            return existingTag;
        }
        name = deleteWhitespace(ValueConverter.convertTagName(name));
        Tag newTag = new Tag(name);
        categories.forEach(tag -> newTag.addCategory(tag));
        return newTag;
    }
}
