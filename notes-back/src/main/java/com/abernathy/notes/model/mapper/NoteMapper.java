package com.abernathy.notes.model.mapper;

import com.abernathy.notes.model.domain.Note;
import com.abernathy.notes.model.dto.NoteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteDTO toNoteDTO(Note patient);

    List<NoteDTO> toNoteDTOs(List<Note> notes);

}
