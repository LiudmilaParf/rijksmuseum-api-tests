
# Rijksmuseum API Tests

This is an automated testing project for the Rijksmuseum public API, built using Java, TestNG, and RestAssured. The project is structured for maintainability, scalability, and reporting with Allure.

## Tech Stack

- **Java 21**
- **TestNG**
- **RestAssured**
- **Allure Reports**
- **GitHub Actions** (CI/CD)
- **Maven** (build management)

## Test Coverage

This project covers the Rijksmuseum public API endpoints with both **positive** and **negative** test scenarios.

### `GET /collection`
Located in: `CollectionTests.java`

Test scenarios:
- ✔️ Get collection with required `key` only (default params)
- ✔️ Get collection filtered by `involvedMaker` (data-driven)
- ✔️ Get collection filtered by `material` (data-driven)
- ✔️ Get collection filtered by multiple parameters (`material` + `involvedMaker`)
- ❌ Negative test: verify that `page * pageSize > 10,000` is not allowed

### `GET /collection/{object-number}`
Located in: `CollectionDetailsTests.java`

Test scenario:
- ✔️ Get detailed information about an art object by valid `objectNumber`  
  _(objectNumber is dynamically retrieved using a valid `involvedMaker`)_

Each test uses:
- ✅ Status code validation
- ✅ Schema-based deserialization (POJOs)
- ✅ Assertions on response content
- 🔄 Reusable actions and configuration

## Project Structure

```
rijksmuseum-api-tests/
├── .github/
│   └── workflows/                               # GitHub Actions workflows
│       ├── regression_ci.yml
│       └── smoke.yml
├── src/
│   └── test/
│       ├── java/
│       │   └── com.rijksmuseum.api/
│       │       ├── actions/                    # Reusable API request methods
│       │       ├── configuration/
│       │       ├── models/
│       │       │   ├── collection/             # POJOs for /collection 
│       │       │   └── collectionDetails/      # POJOs for /collection/{object-number}
│       │       ├── tests/                      # Test classes organized by endpoint
│       │       └── utilities/                  # Helper utilities
│       └── resources/                          # TestNG XML suites and configs
├── pom.xml                                     # Maven build configuration
└── README.md
```

## Running Tests Locally

1. Clone the repository.
2. Create `secrets.properties` in `src/test/resources`:

```
api.key=YOUR_API_KEY
```

3. Run tests with:

```bash
./mvnw clean test
```

4. Generate Allure Report locally:

```bash
./mvnw allure:report
```
The report generated locally can be opened in browser, see [index.html](./target/allure-report/index.html)

## Running in GitHub Actions
This project is fully integrated with **GitHub Actions** for CI execution and Allure report publishing.

The pipeline will:
- Run tests
- Generate Allure report
- Deploy the report to GitHub Pages

> 📌 Make sure to set `API_KEY` and `PAT` in GitHub Secrets for the workflow to succeed.
> 
>
### 🔐 Required GitHub Secrets

Before running tests in CI, make sure the following secrets are configured in your repository:

- `API_KEY` — your Rijksmuseum API key.
- `PAT` — your GitHub **Personal Access Token** with `repo` and `workflow` permissions.  
  This is used to push Allure reports to the `allure-gh-pages` branch.

To add secrets:

1. Go to your GitHub repository
2. Click **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret** and add each key above.

### CI Triggers

The tests will run automatically on:

- Pushes and pull requests to `main` branch
- Manual trigger via **"Run Smoke Tests"** or **"Run Regression Tests"** button on the **Actions** tab

## Allure Report (GitHub Pages)
After successful run, reports are published here:

[View Allure Report](https://liudmilaparf.github.io/rijksmuseum-api-tests/)

---

### Author

Liudmila Parfenova
🔗 [LinkedIn Profile](https://www.linkedin.com/in/liudmilaparfenova/)

