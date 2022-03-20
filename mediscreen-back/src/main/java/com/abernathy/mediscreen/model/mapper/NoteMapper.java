package com.abernathy.mediscreen.model.mapper;

import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.model.dto.NoteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteDTO toNoteDTO(Note note);

    Note toNote(NoteDTO noteDTO);

    List<NoteDTO> toNoteDTOs(List<Note> notes);

}
