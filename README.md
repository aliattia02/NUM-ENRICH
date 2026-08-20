# NUM-ENRICH


## Structure

- `composeApp/` 
  - `src/commonMain` – shared UI code
  - `src/androidMain` – Android-specific code 
- `backend/` –  FastAPI backend, currently just a health check endpoint

## Running the app


```
./gradlew :composeApp:installDebug
```

## Running the backend

```
cd backend
pip install -r requirements.txt
uvicorn main:app --reload
```
