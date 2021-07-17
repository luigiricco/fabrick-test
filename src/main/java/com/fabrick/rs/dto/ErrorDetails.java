package com.fabrick.rs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

/**
 * ErrorDetails
 */
@Builder
public class ErrorDetails implements Serializable {

  @JsonProperty("property")
  private String property;

  @JsonProperty("message")
  private String message;

  public ErrorDetails property(String property) {
    this.property = property;
    return this;
  }

  /**
   * Get property
   * @return property
  */
  @ApiModelProperty(value = "")


  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public ErrorDetails message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDetails errorDetails = (ErrorDetails) o;
    return Objects.equals(this.property, errorDetails.property) &&
        Objects.equals(this.message, errorDetails.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(property, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDetails {\n");
    
    sb.append("    property: ").append(toIndentedString(property)).append('\n');
    sb.append("    message: ").append(toIndentedString(message)).append('\n');
    sb.append('}');
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

