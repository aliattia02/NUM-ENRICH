# NUM-ENRICH

Small Android app for jotting down simple readings (a label and a value).
Built with Kotlin Multiplatform + Jetpack Compose, Android only for now —
the project is laid out so iOS can be added as another source set later.

This is a learning project, so it's intentionally minimal.

## Structure

- `composeApp/` – the Android app
  - `src/commonMain` – shared UI code
  - `src/androidMain` – Android-specific code (just the Activity for now)
- `backend/` – small FastAPI backend, currently just a health check endpoint

## Running the app

Open the project folder in Android Studio and run the `composeApp`
configuration, or from a terminal:

```
./gradlew :composeApp:installDebug
```

## Running the backend

```
cd backend
pip install -r requirements.txt
uvicorn main:app --reload
```
