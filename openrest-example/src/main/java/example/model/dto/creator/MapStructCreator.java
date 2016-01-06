package example.model.dto.creator;

import org.mapstruct.Mapper;

import pl.openrest.dto.mapper.CreateMapper;

public interface MapStructCreator<R, P> extends CreateMapper<R, P> {

    @Override
    public R create(P from);
}
