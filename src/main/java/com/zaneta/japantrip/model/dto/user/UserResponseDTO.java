package com.zaneta.japantrip.model.dto.user;

import java.util.UUID;

public record UserResponseDTO(UUID id, String firstName, String lastName, String nickname, String email) {
}
