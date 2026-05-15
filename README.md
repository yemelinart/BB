# Todo Explorer

Todo Explorer is a native Android application that retrieves todo items from the public JSONPlaceholder REST API, displays them in a scrollable list, and opens a details screen for a selected task.

## Technologies Used

- Kotlin
- Jetpack Compose
- Material 3
- MVVM architecture
- ViewModel
- Kotlin Coroutines
- StateFlow
- Retrofit
- kotlinx.serialization
- Navigation Compose
- Manual dependency injection

## API Endpoint

- List: `GET https://jsonplaceholder.typicode.com/todos`
- Details: `GET https://jsonplaceholder.typicode.com/todos/{id}`

## Main Features

- Fetches todo items from a public REST API after launch
- Displays task ID, title, and completion status in a scrollable list
- Opens a details screen when a task is tapped
- Fetches full todo details by task ID on the details screen
- Handles loading, success, error, and empty states for the list
- Handles loading, success, and error states for details
- Keeps networking and repository logic outside the UI layer

## How to Run

1. Open the project in Android Studio.
2. Let Gradle sync dependencies.
3. Run the `app` configuration on an Android emulator or physical Android device.

Command-line build:

```bash
gradle wrapper --gradle-version 8.9
./gradlew assembleDebug
```

The app needs internet access because it loads data from JSONPlaceholder.

## Screens

### Main List Screen

The main screen shows the app title and a scrollable list of todo tasks. Each row displays:

- Task ID
- Task title
- Completed or not completed status

Tapping any row navigates to the details screen.

### Details Screen

The details screen includes a top app bar with back navigation and fetches the selected todo item by ID. It displays:

- Task ID
- User ID
- Title
- Completion status

## Validation

The project was validated locally with:

```bash
./gradlew assembleDebug
./gradlew lintDebug
```

The GitHub Actions workflow also builds the debug APK.

## Assumptions

- The JSONPlaceholder API is available and returns the expected todo schema.
- The details screen intentionally refetches the selected todo by ID instead of passing the full object through navigation.
- Manual dependency injection is used because the app is small and does not need Hilt-level setup.
