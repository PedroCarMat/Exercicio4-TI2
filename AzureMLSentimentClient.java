import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

public class AzureMLSentimentClient {
    public static void main(String[] args) throws Exception {
        String endpoint = "https://southcentralus.api.azureml.ms/pipelines/v1.0/subscriptions/22fa3f03-34d9-4871-876a-2bb258ade11f/resourceGroups/TesteML/providers/Microsoft.MachineLearningServices/workspaces/pucminassti2MC/PipelineRuns/PipelineEndpointSubmit/Id/a2c412b4-6760-46f3-accd-24d48f2cf998";
        String apiKey = "d8f3a1b2c4e5f67890ab12cd34ef5678";

        String inputJson = """
        {
            "input_data": {
                "columns": ["text"],
                "index": [0],
                "data": ["Este filme é incrível, adorei cada parte!"]
            }
        }
        """;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + apiKey)
            .POST(BodyPublishers.ofString(inputJson, StandardCharsets.UTF_8))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println("Resposta do modelo:");
        System.out.println(response.body());
    }
}
