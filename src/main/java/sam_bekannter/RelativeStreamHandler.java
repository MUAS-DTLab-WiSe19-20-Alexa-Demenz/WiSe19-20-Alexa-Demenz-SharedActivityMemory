/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package sam_bekannter;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.attributes.persistence.impl.DynamoDbPersistenceAdapter;
import sam_bekannter.handlers.*;
import sam_bekannter.model.MyPartitionKeyGenerators;

public class RelativeStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        DynamoDbPersistenceAdapter adapter = DynamoDbPersistenceAdapter
                .builder()
                .withPartitionKeyName("Test")
                .withTableName("TestData")
                .withAutoCreateTable(true)
                .withPartitionKeyGenerator(MyPartitionKeyGenerators.myKey())
                .build();

        return Skills.custom()
                .addRequestHandlers(
                      new MyAgeIsIntentHandler(),
                        new WhatsMyAgeIntentHandler(),
                        new MyHolidayIsIntentHandler(),
                        new WhatsMyHolidayIntentHandler(),
                        new WhatsMyRelationIntentHandler(),
                        new MyRelationIsIntentHandler(),
                        new WhatsMyJobIntentHandler(),
                        new MyJobIsIntentHandler(),
                        new WhatsMyResidenceIntentHandler(),
                        new MyResidenceIsIntentHandler(),
                        new MyNameIsIntentHandler(),
                        new WhatsMyNameIntentHandler(),
                        new LaunchRequestHandlerRelated(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                .withPersistenceAdapter(adapter)
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public RelativeStreamHandler() {
        super(getSkill());
    }

}
