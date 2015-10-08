package heim

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(ScriptManagerService)
class ScriptManagerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
        def heatmap = new File("/tmp/last_heatmap.png")
        if (heatmap.exists()) {
            heatmap.delete()
        }

    }

    void "test reading in a script from Heatmap workflow"() {
        given: "Heatmap workflow and init.r scripts."
        def workflowName = "heatmap"
        def scriptName = "init.r"
        when: "Trying to read in the init.r script."
        def result = service.readScript(workflowName, scriptName)
        then: "Resulting string is not null and consists of 8 lines"
        result
        result.split("\n").size() == 8
    }

    void "test listing workflows"() {
        given: "Only Heatmapworkflow is present"

        when: "listing studies"
        def result = service.listWorkflows()
        then: "Only heatmap workflow is returned in a list"
        result.size() == 1
        result[0] == 'heatmap'
    }
}
