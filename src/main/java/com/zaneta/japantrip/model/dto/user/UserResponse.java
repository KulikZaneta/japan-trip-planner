package com.zaneta.japantrip.model.dto.user;

import java.util.UUID;

public record UserResponse(UUID id, String firstName, String lastName, String nickname, String email) {
}
