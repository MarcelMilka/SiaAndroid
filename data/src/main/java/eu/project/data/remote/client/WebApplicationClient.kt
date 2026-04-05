package eu.project.data.remote.client

import eu.project.data.remote.endpoint.TestEndpoint

internal interface WebApplicationClient {
    val testEndpoint: TestEndpoint
}