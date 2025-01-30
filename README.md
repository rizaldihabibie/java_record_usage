# Java Record Usage

This repository contains notes and examples on how to use Java records. Records are a feature introduced in Java 14 that provides a compact syntax for declaring classes that are intended to be data carriers.

## Prerequisites

- Java 14 or higher
- Gradle

## How to Use

This repository contains code snippets and examples demonstrating how to use Java records effectively in your project. You can explore the code to understand how records can simplify your Java code by making it more concise and readable.
But, from this POC we can conclude that Records is not suitable for a mapper class. While Java records are a powerful feature for creating immutable data structures, using them directly as mappers or Data Transfer Objects (DTOs) may not always be the best choice. Below are some reasons why records may not be suitable for direct use in data mapping or DTO scenarios:

    Lack of Mutability
    Records are immutable by design, meaning their fields cannot be changed after the object is created. This can be a limitation in scenarios where you need to update or populate the DTO after creation.

    No Default Constructor
    Records automatically generate a constructor that initializes all fields. However, some frameworks (like Jackson) require a no-argument constructor to instantiate objects, which records do not provide.

    Reflection and Serialization Issues
    Some libraries that rely on reflection may not fully support records out of the box. While recent versions of libraries like Jackson and MapStruct have better support for records, older versions or certain use cases might still have issues with records.

    No Setter Methods
    Unlike traditional POJOs, records don't have setter methods due to their immutability. This can cause compatibility issues with libraries that rely on setters to populate DTO fields.

    Limited Framework Support
    While more frameworks are adding support for records, some may still not handle records as smoothly as regular POJOs. Additional configurations or custom mappers may be required for compatibility.

    Complexity in Mapping
    For complex mappings or transformations, records may not offer the same flexibility as traditional POJOs with custom constructors or setter methods. You may have to write more complex or non-standard mapping logic.

    Data Validation and Logic
    Records are intended for simple, immutable data. If your DTO requires additional behavior or validation, a traditional POJO may be a better fit.

### Testing the Code

To run the tests and validate the examples, use the following command:

```bash
./gradlew test
